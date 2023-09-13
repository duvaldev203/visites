import { Suspense, lazy, useEffect, useState } from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import { Toaster } from 'react-hot-toast';

import Visite from './pages/Dashboard/Visite';
import SignIn from './pages/Authentication/SignIn';
import Loader from './common/Loader';
import routes from './routes';
import { USER_LOCAL_STORAGE_KEY } from './constants/LOCAL_STORAGE';
import { UserResponse } from './generated';

const DefaultLayout = lazy(() => import('./layout/DefaultLayout'));

function App() {

  const userString = localStorage.getItem(USER_LOCAL_STORAGE_KEY);
  const user: UserResponse = userString ? JSON.parse(userString) : null;

  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    setTimeout(() => setLoading(false), 1000);
  }, []);

  return loading ? (
    <Loader />
  ) : (
    <>
    <Toaster position='top-right' reverseOrder={false} containerClassName='overflow-auto'/>
  
      <Routes>
        <Route path='/auth/signin' element={user ? <Navigate to='/' replace={true}/> : <SignIn />}/>
        <Route element={<DefaultLayout />}>
          <Route index element={<Visite />} />
          {routes.map(({ path, component: Component }) => (
            <Route
              path={path}
              element={
                <Suspense fallback={<Loader />}>
                  <Component />
                </Suspense>
              }
            />
          ))}
        </Route>
      </Routes>
    </>
  );
}

export default App;
