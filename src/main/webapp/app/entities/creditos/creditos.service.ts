import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Creditos } from './creditos.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class CreditosService {

    private resourceUrl = SERVER_API_URL + 'api/creditos';

    constructor(private http: Http) { }

    create(creditos: Creditos): Observable<Creditos> {
        const copy = this.convert(creditos);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(creditos: Creditos): Observable<Creditos> {
        const copy = this.convert(creditos);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Creditos> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to Creditos.
     */
    private convertItemFromServer(json: any): Creditos {
        const entity: Creditos = Object.assign(new Creditos(), json);
        return entity;
    }

    /**
     * Convert a Creditos to a JSON which can be sent to the server.
     */
    private convert(creditos: Creditos): Creditos {
        const copy: Creditos = Object.assign({}, creditos);
        return copy;
    }
}
