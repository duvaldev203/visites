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
/**
 * 
 * @export
 * @interface VisiteurResponse
 */
export interface VisiteurResponse {
    /**
     * 
     * @type {number}
     * @memberof VisiteurResponse
     */
    id?: number;
    /**
     * 
     * @type {string}
     * @memberof VisiteurResponse
     */
    nom?: string;
    /**
     * 
     * @type {string}
     * @memberof VisiteurResponse
     */
    prenom?: string;
    /**
     * 
     * @type {string}
     * @memberof VisiteurResponse
     */
    sexe?: string;
    /**
     * 
     * @type {string}
     * @memberof VisiteurResponse
     */
    email?: string;
    /**
     * 
     * @type {Date}
     * @memberof VisiteurResponse
     */
    dateNais?: Date;
    /**
     * 
     * @type {string}
     * @memberof VisiteurResponse
     */
    tel?: string;
    /**
     * 
     * @type {string}
     * @memberof VisiteurResponse
     */
    profession?: string;
    /**
     * 
     * @type {Date}
     * @memberof VisiteurResponse
     */
    createdAt?: Date;
    /**
     * 
     * @type {Date}
     * @memberof VisiteurResponse
     */
    updatedAt?: Date;
}