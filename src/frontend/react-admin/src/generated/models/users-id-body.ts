/* tslint:disable */
/* eslint-disable */
/**
 * Tracking Application
 * Backend APIs pour une application de Tacking Visites
 *
 * OpenAPI spec version: 1.0
 * Contact: donfackduval@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { UserRequest } from './user-request';
/**
 * 
 * @export
 * @interface UsersIdBody
 */
export interface UsersIdBody {
    /**
     * 
     * @type {UserRequest}
     * @memberof UsersIdBody
     */
    user?: UserRequest;
    /**
     * 
     * @type {Blob}
     * @memberof UsersIdBody
     */
    image: Blob;
}
