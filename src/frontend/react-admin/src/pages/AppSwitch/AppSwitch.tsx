import React from "react";
import { connect, useSelector } from "react-redux";
import { Route, Routes } from "react-router-dom";
import { ReduxProps } from "../../redux/configureStore";
import SignIn from "../Authentication/SignIn";
import NotFound from "./NotFound";
import Visite from '../Dashboard/Visite'
import Calendar from "../Calendar";
import Bureau from "../Bureau/Bureau";
import FormElements from "../Form/FormElements";
import FormLayout from "../Form/FormLayout";
import Tables from "../Tables";
import Settings from "../Settings";
import Chart from "../Chart";
import Alerts from "../UiElements/Alerts";
import Buttons from "../UiElements/Buttons";
import Profile from "../Profile";
import Role from "../Role/Role";

interface AppSwitchProps {
    isLoggedIn: boolean;
}

const AppSwitch: React.FC<AppSwitchProps> = (props) => {
    const state = useSelector((state: ReduxProps) => state);
    console.log('isLoggedIn ' + props.isLoggedIn);
    console.log('state loggedIn ' + state.loggedIn);
  
    if (
      (props.isLoggedIn && props.isLoggedIn === true) ||
      (state.loggedIn && state.loggedIn === true)
    ) {
      return (
        <Routes>
          <Route index element={<Visite />} />
          <Route path="/profile" element={<Profile />} />
          {/* <Route path="/contacts" element={<Contact />} /> */}
          <Route path="/calendar" element={<Calendar />} />
          <Route path="/forms/form-elements" element={<FormElements />} />
          <Route path="/forms/form-layouts" element={<FormLayout />} />
          <Route path="/tables" element={<Tables />} />
          <Route path="/settings" element={<Settings />} />
          <Route path="/chart" element={<Chart />} />
          <Route path="/ui/alerts" element={<Alerts />} />
          <Route path="/ui/buttons" element={<Buttons />} />

          {/* Site Components */}
          <Route path="/bureau" element={<Bureau />} />
          <Route path="/role" element={<Role />} />
          
          <Route path="*" element={<NotFound />}/>
        </Routes>
      );
    } else {
      return (
        <Routes>
          <Route path="/auth/signin" element={<SignIn />} />
          <Route path="*" element={<SignIn />} />
        </Routes>
      );
    }
  };

function mapStateToProps(state: ReduxProps): ReduxProps {
    return {
      user: state.user,
      environment: state.environment,
      loggedIn: state.loggedIn,
    };
}

export default connect(mapStateToProps)(AppSwitch);