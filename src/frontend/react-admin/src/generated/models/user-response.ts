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
import { BureauResponse } from './bureau-response';
import { ProfileResponse } from './profile-response';
import { RoleResponse } from './role-response';
/**
 * 
 * @export
 * @interface UserResponse
 */
export interface UserResponse {
    /**
     * 
     * @type {number}
     * @memberof UserResponse
     */
    id?: number;
    /**
     * 
     * @type {string}
     * @memberof UserResponse
     */
    nom?: string;
    /**
     * 
     * @type {string}
     * @memberof UserResponse
     */
    prenom?: string;
    /**
     * 
     * @type {string}
     * @memberof UserResponse
     */
    sexe?: string;
    /**
     * 
     * @type {string}
     * @memberof UserResponse
     */
    email?: string;
    /**
     * 
     * @type {Date}
     * @memberof UserResponse
     */
    dateNais?: Date;
    /**
     * 
     * @type {string}
     * @memberof UserResponse
     */
    tel?: string;
    /**
     * 
     * @type {string}
     * @memberof UserResponse
     */
    poste?: string;
    /**
     * 
     * @type {string}
     * @memberof UserResponse
     */
    username?: string;
    /**
     * 
     * @type {string}
     * @memberof UserResponse
     */
    password?: string;
    /**
     * 
     * @type {ProfileResponse}
     * @memberof UserResponse
     */
    profile?: ProfileResponse;
    /**
     * 
     * @type {BureauResponse}
     * @memberof UserResponse
     */
    bureau?: BureauResponse;
    /**
     * 
     * @type {Array<RoleResponse>}
     * @memberof UserResponse
     */
    roles?: Array<RoleResponse>;
    /**
     * 
     * @type {Date}
     * @memberof UserResponse
     */
    createdAt?: Date;
    /**
     * 
     * @type {Date}
     * @memberof UserResponse
     */
    updatedAt?: Date;
}