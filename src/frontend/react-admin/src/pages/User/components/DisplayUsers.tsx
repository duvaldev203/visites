import { connect, useDispatch, useSelector } from 'react-redux';
import { UserControllerApi, UserResponse } from '../../../generated';
import { ReduxProps } from '../../../redux/configureStore';
import { useCallback, useEffect, useState } from 'react';
import { MODAL_MODE } from '../../../constants/APP_CONSTANTS';
import { Link } from 'react-router-dom';
import { EditIcon, NewIcon, NextIcon, PreviousIcon, TrashIcon } from '../../../constants/Icon';

import CreateOrUpdateUserModal from './CreateOrUpdateUserModal';

import { TOKEN_LOCAL_STORAGE_KEY } from '../../../constants/LOCAL_STORAGE';
import { DeleteItemModal } from '../../../constants/DeleteItemModal';
import { GridIndicator } from '../../../constants/GridIndicator';

import './DisplayUsers.css'

interface DisplayUsersProps {
  users: UserResponse[],
  isLoading: boolean,

  setShowSuccessNotif: (value: boolean) => void,
  setSuccessNotifMessage: (value: string) => void,
  setSuccessNotifDescription: (value: string | null) => void,

  setShowWarning: (value: boolean) => void,
  setWarningMessage: (value: string) => void,
  setWarningNotifDescription: (value: string | null) => void,

  setShowDangerNotif: (value: boolean) => void,
  setDangerNotifMessage: (value: string) => void,
  setDangerNotifDescription: (value: string | null) => void,
}

import { DangerNotification } from '../../../services/Notification.service';
import ReactPaginate from 'react-paginate';
import { BASE_PATH } from '../../../generated/base';

const DisplayUsers: React.FC<DisplayUsersProps> = (props) => {
  const state = useSelector((state: ReduxProps) => state);
  const [listeUsers, setListeUsers] = useState<UserResponse[]>(props.users);
  const [userS, setUserS] = useState<UserResponse | null>(props.users[0]);
  const [selectedUser, setSelectedUser] = useState<UserResponse>({});
  // Pagination
  const [currentPage, setCurrentPage] = useState(0);
  const usersPerPage = 10;

  const offset = currentPage * usersPerPage;
  const currentUsers = listeUsers.slice(offset, offset + usersPerPage);

  const pageCount = Math.ceil(listeUsers.length / usersPerPage);

  const handlePageClick = (data: { selected: any; }) => {
    const selected = data.selected;
    setCurrentPage(selected);
  };
  // End Pagination

  const [showCreateOrUpdateModal, setShowCreateOrUpdateModal] = useState(false);
  const [showDeleteModal, setShowDeleteModal] = useState(false);

  const [modalMode, setModalMode] = useState<MODAL_MODE>(MODAL_MODE.create);
  const [modalTitle, setModalTitle] = useState<string>("");
  const [showIndicator, setShowIndicator] = useState<boolean>(false);
  const [isError, setIsError] = useState(false);
  const [isErrorDescription, setIsErrorDescription] = useState('');

  const dispatch = useDispatch();

  const onReady = useCallback(() => {
    const token: string = localStorage.getItem(TOKEN_LOCAL_STORAGE_KEY)!;
    const usersApi = new UserControllerApi({ ...state.environment, accessToken: token });

    setShowIndicator(true)

    usersApi.index1()
      .then((response) => {
        if (response && response.data) {
          if (response.status === 200) {
            setListeUsers(response.data);
          }
        }
      })
      .catch((error) => {
        setIsError(true);
        console.log(isError)
        if (error?.response && error?.response?.data) {
          setIsErrorDescription(error?.response?.data?.message);
        } else {
          if (error.response && error.response.status === 403) {
            setIsErrorDescription('Vous n\'avez pas le d\'effectuer cette requete');
          } else {
            setIsErrorDescription('Probleme lors de la recuperation des users')
          }
        }

        // Handle Warning Notif 
        // props.setShowWarning(true)
      })
      .finally(() => {
        setShowIndicator(false)
        setTimeout(() => {
          setIsError(false)
          props.setShowSuccessNotif(false)
        }, 3000);
      });
  }, [dispatch]);

  useEffect(() => {
    onReady();

    setShowDeleteModal(false);
  }, []);

  //Create User
  const handleNewItem = () => {
    setUserS(null);
    setShowCreateOrUpdateModal(true);
    setModalMode(MODAL_MODE.create)
    setModalTitle("Créer un nouveau employe")
  }

  const handleCloseCreateOrUpdateModal = () => {
    setShowCreateOrUpdateModal(false)
  }
  // Update User
  const handleEdit = (userSelected: UserResponse) => {
    setUserS(userSelected);
    setModalMode(MODAL_MODE.update)
    setModalTitle("Modifier le user")
    setShowCreateOrUpdateModal(true);
  };

  // Delete User
  const handleDelete = (user: UserResponse) => {
    setSelectedUser(user);
    setShowDeleteModal(true)
  };

  const handleCloseDeleteModal = () => {
    setShowDeleteModal(false)
  }

  const handleConfirmDeletingModal = () => {
    proccessDeleteItem()
  }

  const proccessDeleteItem = () => {
    setShowDeleteModal(false)

    const token: string = localStorage.getItem(TOKEN_LOCAL_STORAGE_KEY)!;
    const usersApi = new UserControllerApi({ ...state.environment, accessToken: token });

    setShowIndicator(true)

    usersApi.delete2(selectedUser.id!)
      .then((response) => {
        if (response) {
          if (response.status === 204) {
            console.log('response :', response)
            setShowDeleteModal(false)
            props.setSuccessNotifMessage("Succes")
            props.setSuccessNotifDescription('Ce user a ete supprime avec succes')
            props.setShowSuccessNotif(true)
          }
        }
      })
      .catch((error) => {
        setIsError(true);
        if (error?.response && error?.response?.data) {
          setIsErrorDescription(error?.response?.data?.message);
        } else {
          if (error.response && error.response.status === 403) {
            setIsErrorDescription('Vous n\'avez pas le d\'effectuer cette requete');
          } else {
            setIsErrorDescription('Probleme lors de la suppression du user')
          }
        }
      })
      .finally(() => {

        setShowIndicator(false)

        // notification
        setTimeout(() => {
          props.setShowSuccessNotif(false)
          props.setShowDangerNotif(false)
          setIsError(false)
        }, 3000);
      });

  }

  const shared_class: string = 'rounded-md inline-flex items-center justify-center gap-2.5 py-2 sm:py-2  px-1 text-center font-medium text-white hover:bg-opacity-90 sm:px-2 md:px-3 lg:px-3 xl:px-3'

  return (
    <div>
      <div className={`absolute bg-black w-[90%]`}>
        {isError && <DangerNotification
          message={'Error'}
          description={isErrorDescription}
        />}
      </div>

      <div className="mb-3.5 flex flex-wrap gap-1 xl:gap-3 justify-end">
        <Link onClick={handleNewItem} to="#" className={`${shared_class} hover:opacity-80`} style={{ backgroundColor: '#057a4f' }}>
          <div className={'w-5 h-5 -mr-1'}>
            <NewIcon size={2} color='#fff' />
          </div>
          Nouveau
        </Link>
      </div>

      {showCreateOrUpdateModal && <CreateOrUpdateUserModal
        mode={modalMode}
        title={modalTitle}
        onClose={handleCloseCreateOrUpdateModal}
        refresh={onReady}
        item={modalMode !== MODAL_MODE.create ? userS : null}
        setShowSuccessNotif={props.setShowSuccessNotif}
        setSuccessNotifMessage={props.setSuccessNotifMessage}
        setSuccessNotifDescription={props.setSuccessNotifDescription}
        setShowWarning={props.setShowWarning}
        setWarningMessage={props.setWarningMessage}
        setWarningNotifDescription={props.setWarningNotifDescription}
      />}
      {showDeleteModal && <DeleteItemModal
        isVisible={true}
        itemName={'User d\'id ' + selectedUser.id}
        onClose={handleCloseDeleteModal}
        refresh={onReady}
        onConfirm={handleConfirmDeletingModal}
      />}

      <div className="rounded-sm border border-stroke bg-white px-5 pt-6 pb-2.5 shadow-default dark:border-strokedark dark:bg-boxdark sm:px-7.5 xl:pb-1">
        <div className="max-w-full overflow-x-auto">
          <table className="w-full table-auto">
            <thead>
              <tr className="bg-gray-2 text-left dark:bg-meta-4 border-b">
                <th className="py-4 px-4 font-medium text-black dark:text-white">
                  Profile
                </th>
                <th className="py-4 px-4 font-medium text-black dark:text-white">
                  Username
                </th>
                <th className="py-4 px-4 font-medium text-black dark:text-white">
                  Nom
                </th>
                <th className="py-4 px-4 font-medium text-black dark:text-white">
                  Prenom
                </th>
                <th className="py-4 px-4 font-medium text-black dark:text-white">
                  Email
                </th>
                <th className="py-4 px-4 font-medium text-black dark:text-white">
                  Sexe
                </th>
                <th className="py-4 px-4 font-medium text-black dark:text-white">
                  Telephone
                </th>
                <th className="py-4 px-4 font-medium text-black dark:text-white">
                  Date de Naissance
                </th>
                <th className="py-4 px-4 font-medium text-black dark:text-white">
                  Poste
                </th>
                <th className="py-4 px-4 font-medium text-black dark:text-white">
                  Bureau
                </th>
                <th className="py-4 px-4 font-medium text-black dark:text-white">
                  Roles
                </th>
                <th className="py-4 px-4 font-medium text-black dark:text-white">
                  Actions
                </th>
              </tr>
            </thead>
            <tbody>
              {showIndicator && <GridIndicator />}
              {listeUsers.length === 0 ? (
                <tr className='border-b'>
                  <td colSpan={11} className='text-center p-2'>Aucun user pour le moment</td>
                </tr>
              ) : (
                currentUsers!.map((item) => (
                  <tr key={item.id} className='border-b'>
                    <td className="py-2 px-2">
                      <img className='h-12 rounded-full justify-center text-center' src={BASE_PATH + '/users/profile/' + item.profile?.id} alt="User Profile" />
                    </td>
                    <td className="py-2 px-2">
                      <p className="text-black dark:text-white">{item.username}</p>
                    </td>
                    <td className="py-2 px-2">
                      <p className="text-black dark:text-white">{item.nom}</p>
                    </td>
                    <td className="py-2 px-2">
                      <p className="text-black dark:text-white">{item.prenom}</p>
                    </td>
                    <td className="py-2 px-2">
                      <p className="text-black dark:text-white">{item.email}</p>
                    </td>
                    <td className="py-2 px-2">
                      <p className="text-black dark:text-white">{item.sexe}</p>
                    </td>
                    <td className="py-2 px-2">
                      <p className="text-black dark:text-white">{item.tel}</p>
                    </td>
                    <td className="py-2 px-2">
                      <p className="text-black dark:text-white">{new Date(item.dateNais!).toLocaleDateString()!}</p>
                    </td>
                    <td className="py-2 px-2">
                      <p className="text-black dark:text-white">{item.poste}</p>
                    </td>
                    <td className="py-2 px-2">{item.bureau && 
                      <p className="text-black dark:text-white">{item.bureau.batiment + '-' + item.bureau.etage + '-' + item.bureau.porte}</p>
                      }
                    </td>
                    <td className="py-2 px-2">{item.roles?.map((role) => (
                      <p className="text-black dark:text-white">{role.nom}</p>
                      ))}
                    </td>
                    <td className="py-2 px-5">
                      <div className="flex items-center space-x-4">
                        <button className="hover:text-primary" title='Supprimer' onClick={() => handleDelete(item)}>
                          <TrashIcon size={17} />
                        </button>
                        <button
                          className="hover:text-primary"
                          title="Modifier"
                          onClick={() => handleEdit(item)}
                        >
                          <EditIcon size={17} />
                        </button>
                      </div>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
          <ReactPaginate
            className={`inline-flex mt-3 justify-end w-full font-bold pr-5`}
            previousLabel={<PreviousIcon size={15} class='mt-1 hover:opacity-90 hover:text-primary mr-2' />}
            nextLabel={<NextIcon size={15} class='mt-1 hover:opacity-90 hover:text-primary ml-2' />}
            breakLabel={'...'}
            pageCount={pageCount}
            marginPagesDisplayed={5}
            pageRangeDisplayed={5}
            onPageChange={handlePageClick}
            containerClassName={'pagination'}
            activeClassName={'text-primary border-t-2 bg-gray dark:bg-graydark'}
            pageLinkClassName={'hover:opacity-90 hover:text-primary hover:bg-gray  dark:hover:bg-graydark hover:border-t-2 px-3'}
          />
      </div>
    </div>
  );
};

function mapStateToProps(state: ReduxProps): ReduxProps {
  return {
    user: state.user,
    environment: state.environment,
    loggedIn: state.loggedIn,
    access_token: state.access_token,
  };
}
export default connect(mapStateToProps)(DisplayUsers)
