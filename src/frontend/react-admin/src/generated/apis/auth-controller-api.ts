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
import globalAxios, { AxiosResponse, AxiosInstance, AxiosRequestConfig } from 'axios';
import { Configuration } from '../configuration';
// Some imports not used depending on template conditions
// @ts-ignore
import { BASE_PATH, COLLECTION_FORMATS, RequestArgs, BaseAPI, RequiredError } from '../base';
import { SignInRequest } from '../models';
import { SignResponse } from '../models';
import { UserRequest } from '../models';
/**
 * AuthControllerApi - axios parameter creator
 * @export
 */
export const AuthControllerApiAxiosParamCreator = function (configuration?: Configuration) {
    return {
        /**
         * 
         * @param {SignInRequest} body 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        login: async (body: SignInRequest, options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'body' is not null or undefined
            if (body === null || body === undefined) {
                throw new RequiredError('body','Required parameter body was null or undefined when calling login.');
            }
            const localVarPath = `/login`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, 'https://example.com');
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }
            const localVarRequestOptions :AxiosRequestConfig = { method: 'POST', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;

            // authentication bearerAuth required
            // http bearer authentication required
            if (configuration && configuration.accessToken) {
                const accessToken = typeof configuration.accessToken === 'function'
                    ? await configuration.accessToken()
                    : await configuration.accessToken;
                localVarHeaderParameter["Authorization"] = "Bearer " + accessToken;
            }

            localVarHeaderParameter['Content-Type'] = 'application/json';

            const query = new URLSearchParams(localVarUrlObj.search);
            for (const key in localVarQueryParameter) {
                query.set(key, localVarQueryParameter[key]);
            }
            for (const key in options.params) {
                query.set(key, options.params[key]);
            }
            localVarUrlObj.search = (new URLSearchParams(query)).toString();
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};
            const needsSerialization = (typeof body !== "string") || localVarRequestOptions.headers['Content-Type'] === 'application/json';
            localVarRequestOptions.data =  needsSerialization ? JSON.stringify(body !== undefined ? body : {}) : (body || "");

            return {
                url: localVarUrlObj.pathname + localVarUrlObj.search + localVarUrlObj.hash,
                options: localVarRequestOptions,
            };
        },
        /**
         * 
         * @param {UserRequest} body 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        register: async (body: UserRequest, options: AxiosRequestConfig = {}): Promise<RequestArgs> => {
            // verify required parameter 'body' is not null or undefined
            if (body === null || body === undefined) {
                throw new RequiredError('body','Required parameter body was null or undefined when calling register.');
            }
            const localVarPath = `/register`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, 'https://example.com');
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }
            const localVarRequestOptions :AxiosRequestConfig = { method: 'POST', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;

            // authentication bearerAuth required
            // http bearer authentication required
            if (configuration && configuration.accessToken) {
                const accessToken = typeof configuration.accessToken === 'function'
                    ? await configuration.accessToken()
                    : await configuration.accessToken;
                localVarHeaderParameter["Authorization"] = "Bearer " + accessToken;
            }

            localVarHeaderParameter['Content-Type'] = 'application/json';

            const query = new URLSearchParams(localVarUrlObj.search);
            for (const key in localVarQueryParameter) {
                query.set(key, localVarQueryParameter[key]);
            }
            for (const key in options.params) {
                query.set(key, options.params[key]);
            }
            localVarUrlObj.search = (new URLSearchParams(query)).toString();
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};
            const needsSerialization = (typeof body !== "string") || localVarRequestOptions.headers['Content-Type'] === 'application/json';
            localVarRequestOptions.data =  needsSerialization ? JSON.stringify(body !== undefined ? body : {}) : (body || "");

            return {
                url: localVarUrlObj.pathname + localVarUrlObj.search + localVarUrlObj.hash,
                options: localVarRequestOptions,
            };
        },
    }
};

/**
 * AuthControllerApi - functional programming interface
 * @export
 */
export const AuthControllerApiFp = function(configuration?: Configuration) {
    return {
        /**
         * 
         * @param {SignInRequest} body 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async login(body: SignInRequest, options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => Promise<AxiosResponse<SignResponse>>> {
            const localVarAxiosArgs = await AuthControllerApiAxiosParamCreator(configuration).login(body, options);
            return (axios: AxiosInstance = globalAxios, basePath: string = BASE_PATH) => {
                const axiosRequestArgs :AxiosRequestConfig = {...localVarAxiosArgs.options, url: basePath + localVarAxiosArgs.url};
                return axios.request(axiosRequestArgs);
            };
        },
        /**
         * 
         * @param {UserRequest} body 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async register(body: UserRequest, options?: AxiosRequestConfig): Promise<(axios?: AxiosInstance, basePath?: string) => Promise<AxiosResponse<SignResponse>>> {
            const localVarAxiosArgs = await AuthControllerApiAxiosParamCreator(configuration).register(body, options);
            return (axios: AxiosInstance = globalAxios, basePath: string = BASE_PATH) => {
                const axiosRequestArgs :AxiosRequestConfig = {...localVarAxiosArgs.options, url: basePath + localVarAxiosArgs.url};
                return axios.request(axiosRequestArgs);
            };
        },
    }
};

/**
 * AuthControllerApi - factory interface
 * @export
 */
export const AuthControllerApiFactory = function (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    return {
        /**
         * 
         * @param {SignInRequest} body 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async login(body: SignInRequest, options?: AxiosRequestConfig): Promise<AxiosResponse<SignResponse>> {
            return AuthControllerApiFp(configuration).login(body, options).then((request) => request(axios, basePath));
        },
        /**
         * 
         * @param {UserRequest} body 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async register(body: UserRequest, options?: AxiosRequestConfig): Promise<AxiosResponse<SignResponse>> {
            return AuthControllerApiFp(configuration).register(body, options).then((request) => request(axios, basePath));
        },
    };
};

/**
 * AuthControllerApi - object-oriented interface
 * @export
 * @class AuthControllerApi
 * @extends {BaseAPI}
 */
export class AuthControllerApi extends BaseAPI {
    /**
     * 
     * @param {SignInRequest} body 
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AuthControllerApi
     */
    public async login(body: SignInRequest, options?: AxiosRequestConfig) : Promise<AxiosResponse<SignResponse>> {
        return AuthControllerApiFp(this.configuration).login(body, options).then((request) => request(this.axios, this.basePath));
    }
    /**
     * 
     * @param {UserRequest} body 
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AuthControllerApi
     */
    public async register(body: UserRequest, options?: AxiosRequestConfig) : Promise<AxiosResponse<SignResponse>> {
        return AuthControllerApiFp(this.configuration).register(body, options).then((request) => request(this.axios, this.basePath));
    }
}
