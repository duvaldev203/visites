import { combineReducers } from 'redux';
import { legacy_createStore as createStore} from 'redux'
import BureauxReducer from '../Reducers/BureauxReducer';

const rootReducer = combineReducers({
  bureaux: BureauxReducer,
  // ... Ajoutez d'autres réducteurs ici si nécessaire
});

const store = createStore(rootReducer);

export default store;
