import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { PlayRequest } from './play-request.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class PlayRequestService {

    private resourceUrl = SERVER_API_URL + 'api/play-requests';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(playRequest: PlayRequest): Observable<PlayRequest> {
        const copy = this.convert(playRequest);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(playRequest: PlayRequest): Observable<PlayRequest> {
        const copy = this.convert(playRequest);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<PlayRequest> {
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
     * Convert a returned JSON object to PlayRequest.
     */
    private convertItemFromServer(json: any): PlayRequest {
        const entity: PlayRequest = Object.assign(new PlayRequest(), json);
        entity.timestamp = this.dateUtils
            .convertDateTimeFromServer(json.timestamp);
        return entity;
    }

    /**
     * Convert a PlayRequest to a JSON which can be sent to the server.
     */
    private convert(playRequest: PlayRequest): PlayRequest {
        const copy: PlayRequest = Object.assign({}, playRequest);

        copy.timestamp = this.dateUtils.toDate(playRequest.timestamp);
        return copy;
    }
}
