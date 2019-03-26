import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Parcelle } from 'app/shared/model/parcelle.model';
import { ParcelleService } from './parcelle.service';
import { ParcelleComponent } from './parcelle.component';
import { ParcelleDetailComponent } from './parcelle-detail.component';
import { ParcelleUpdateComponent } from './parcelle-update.component';
import { ParcelleDeletePopupComponent } from './parcelle-delete-dialog.component';
import { IParcelle } from 'app/shared/model/parcelle.model';

@Injectable({ providedIn: 'root' })
export class ParcelleResolve implements Resolve<IParcelle> {
    constructor(private service: ParcelleService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IParcelle> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Parcelle>) => response.ok),
                map((parcelle: HttpResponse<Parcelle>) => parcelle.body)
            );
        }
        return of(new Parcelle());
    }
}

export const parcelleRoute: Routes = [
    {
        path: '',
        component: ParcelleComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Parcelles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ParcelleDetailComponent,
        resolve: {
            parcelle: ParcelleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Parcelles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ParcelleUpdateComponent,
        resolve: {
            parcelle: ParcelleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Parcelles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ParcelleUpdateComponent,
        resolve: {
            parcelle: ParcelleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Parcelles'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const parcellePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ParcelleDeletePopupComponent,
        resolve: {
            parcelle: ParcelleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Parcelles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
