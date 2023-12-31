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
import { UserResponse } from './user-response';
import { VisiteurResponse } from './visiteur-response';
/**
 * 
 * @export
 * @interface VisiteRequest
 */
export interface VisiteRequest {
    /**
     * 
     * @type {string}
     * @memberof VisiteRequest
     */
    motif?: string;
    /**
     * 
     * @type {string}
     * @memberof VisiteRequest
     */
    heureDebut?: string;
    /**
     * 
     * @type {string}
     * @memberof VisiteRequest
     */
    heureFin?: string;
    /**
     * 
     * @type {Date}
     * @memberof VisiteRequest
     */
    dateVisite?: Date;
    /**
     * 
     * @type {string}
     * @memberof VisiteRequest
     */
    type?: string;
    /**
     * 
     * @type {UserResponse}
     * @memberof VisiteRequest
     */
    user?: UserResponse;
    /**
     * 
     * @type {VisiteurResponse}
     * @memberof VisiteRequest
     */
    visiteur?: VisiteurResponse;
}
