import { Suspense, lazy, useEffect, useState } from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import { Toaster } from 'react-hot-toast';

import Visite from './pages/Dashboard/Visite';
import SignIn from './pages/Authentication/SignIn';
import Loader from './common/Loader';
import { IS_LOGGED_LOCAL_STORAGE_KEY, USER_LOCAL_STORAGE_KEY } from './constants/LOCAL_STORAGE';
import { UserResponse } from './generated';
import AppSwitch from './pages/AppSwitch/AppSwitch';

const DefaultLayout = lazy(() => import('./layout/DefaultLayout'));

function App() {

  const userString = localStorage.getItem(USER_LOCAL_STORAGE_KEY);
  const user: UserResponse = userString ? JSON.parse(userString) : null;
  const isLoggedIn: boolean = Boolean(localStorage.getItem(IS_LOGGED_LOCAL_STORAGE_KEY));  

  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    setTimeout(() => setLoading(false), 1000);
  }, []);

  return loading ? (
    <Loader />
  ) : (
    <>
      <Toaster position='top-right' reverseOrder={false} containerClassName='overflow-auto'/>
      <AppSwitch isLoggedIn={isLoggedIn}/>
    </>
  );

  // return loading ? (
  //   <Loader />
  // ) : (
  //   <>
  //   <Toaster position='top-right' reverseOrder={false} containerClassName='overflow-auto'/>
  //     <Routes>
  //       {!isLoggedIn && <Route path='/auth/signin' element={<SignIn />}/>}
  //       <Route element={!isLoggedIn ? <Navigate to='/auth/signin' replace={true}/> : <DefaultLayout />}>
  //         <Route index element={<Visite />} />
  //         {routes.map(({ path, component: Component }) => (
  //           <Route
  //             path={path}
  //             element={
  //               <Suspense fallback={<Loader />}>
  //                 <Component />
  //               </Suspense>
  //             }
  //           />
  //         ))}
  //       </Route>
  //     </Routes>
  //   </>
  // );
}

export default App;
