
import { TOKEN_LOCAL_STORAGE_KEY } from "./LOCAL_STORAGE";
import { UserControllerApi, VisiteControllerApi, VisiteurControllerApi } from "../generated";

const token: string = localStorage.getItem(TOKEN_LOCAL_STORAGE_KEY)!;
const visiteApi = new VisiteControllerApi({ accessToken: token });
const userApi = new UserControllerApi({ accessToken: token });
const visiteurApi = new VisiteurControllerApi({ accessToken: token });

const getUser = async (): Promise<number> => {
  return userApi.getTotalUsers().then((response) => {
    return response.data;
  });
};
const users = await getUser();

const getVisiteurs = async (): Promise<number> => {
  return visiteurApi.getTotalVisiteurs().then((response) => {
    return response.data;
  });
};
const visiteurs = await getVisiteurs();

const getVisites = async (): Promise<number[]> => {
  return visiteApi.getTotalVisites().then((response) => {
    return response.data;
  });
};
const visites = await getVisites();

export enum stat {
  user = users,
  visiteur = visiteurs,
  visite = visites[0],
  rdv = visites[1],
  freqVisites = parseFloat(((visites[0] / visiteurs) * 100).toFixed(3)),
  freqRDV = parseFloat(((visites[1] / visiteurs) * 100).toFixed(3)),
}