import { Injectable, Inject } from '@angular/core';
import { Http, Headers, Response, ResponseContentType, RequestMethod } from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class CrudService {
    _http: Http;
    _urlBase: string;

    constructor( @Inject( Http ) crudHttp: Http ) {
        this._http = crudHttp;
    }

    setParams( urlBase: string ) {
        this._urlBase = '' + urlBase;
    }

    index() {
        return this._http.post( this._urlBase + '-index.action', {} ).map(( response: Response ) => response.json() );
    }

    edit( accion: string, id: any ) {
        return this._http.post( this._urlBase + '-edit.action', { accion: accion, model: id } )
            .map(( response: Response ) => response.json() );
    }

    save( accion: string, item: any ) {
        return this._http.post( this._urlBase + '-save.action', { accion: accion, model: item } )
            .map(( response: Response ) => response.json() );
    }

    remove( item: any ) {
        return this._http.post( this._urlBase + '-remove.action', { model: item } )
            .map(( response: Response ) => response.json() );
    }

    filter( searchCriteria: any ) {
        return this._http.post( this._urlBase + '-filter.action', { model: searchCriteria } )
            .map(( response: Response ) => response.json() );
    }

    list( searchCriteria: any ) {
        return this._http.post( this._urlBase + '-list.action', { model: searchCriteria } )
            .map(( response: Response ) => response.json() );
    }

    listPage( searchCriteria: any, page: number, limit: number ) {
        return this._http.post( this._urlBase + '-list.action', { model: searchCriteria, page: page, limit: limit } )
            .map(( response: Response ) => response.json() );
    }

    detail( id: any ) {
        return this._http.post( this._urlBase + '-detail.action', { model: id } )
            .map(( response: Response ) => response.json() );
    }

    typeahead( searchCriteria: any ) {
        return this._http.post( this._urlBase + '-typeahead.action', { model: searchCriteria } )
            .map(( response: Response ) => response.json() );
    }

    xlsExport( searchCriteria: any, filename: string ) {
        return this._http.post( this._urlBase + '-xls-export.action', { model: searchCriteria }, {
            responseType: ResponseContentType.Blob,
            headers: new Headers()
        } )
            .map(( response: Response ) => response.blob() );
    }
}
