import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GreleApplicationSharedModule } from 'app/shared';
import {
    ParcelleComponent,
    ParcelleDetailComponent,
    ParcelleUpdateComponent,
    ParcelleDeletePopupComponent,
    ParcelleDeleteDialogComponent,
    parcelleRoute,
    parcellePopupRoute
} from './';

const ENTITY_STATES = [...parcelleRoute, ...parcellePopupRoute];

@NgModule({
    imports: [GreleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ParcelleComponent,
        ParcelleDetailComponent,
        ParcelleUpdateComponent,
        ParcelleDeleteDialogComponent,
        ParcelleDeletePopupComponent
    ],
    entryComponents: [ParcelleComponent, ParcelleUpdateComponent, ParcelleDeleteDialogComponent, ParcelleDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GreleApplicationParcelleModule {}
