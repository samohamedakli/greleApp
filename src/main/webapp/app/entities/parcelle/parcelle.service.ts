import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IParcelle } from 'app/shared/model/parcelle.model';

type EntityResponseType = HttpResponse<IParcelle>;
type EntityArrayResponseType = HttpResponse<IParcelle[]>;

@Injectable({ providedIn: 'root' })
export class ParcelleService {
    public resourceUrl = SERVER_API_URL + 'api/parcelles';

    constructor(protected http: HttpClient) {}

    create(parcelle: IParcelle): Observable<EntityResponseType> {
        return this.http.post<IParcelle>(this.resourceUrl, parcelle, { observe: 'response' });
    }

    update(parcelle: IParcelle): Observable<EntityResponseType> {
        return this.http.put<IParcelle>(this.resourceUrl, parcelle, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IParcelle>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IParcelle[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
