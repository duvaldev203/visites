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
 * @interface VisiteurRequest
 */
export interface VisiteurRequest {
    /**
     * 
     * @type {string}
     * @memberof VisiteurRequest
     */
    nom?: string;
    /**
     * 
     * @type {string}
     * @memberof VisiteurRequest
     */
    prenom?: string;
    /**
     * 
     * @type {string}
     * @memberof VisiteurRequest
     */
    sexe?: string;
    /**
     * 
     * @type {string}
     * @memberof VisiteurRequest
     */
    email?: string;
    /**
     * 
     * @type {Date}
     * @memberof VisiteurRequest
     */
    dateNais?: Date;
    /**
     * 
     * @type {string}
     * @memberof VisiteurRequest
     */
    tel?: string;
    /**
     * 
     * @type {string}
     * @memberof VisiteurRequest
     */
    profession?: string;
}
