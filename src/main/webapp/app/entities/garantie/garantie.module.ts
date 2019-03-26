import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GreleApplicationSharedModule } from 'app/shared';
import {
    GarantieComponent,
    GarantieDetailComponent,
    GarantieUpdateComponent,
    GarantieDeletePopupComponent,
    GarantieDeleteDialogComponent,
    garantieRoute,
    garantiePopupRoute
} from './';

const ENTITY_STATES = [...garantieRoute, ...garantiePopupRoute];

@NgModule({
    imports: [GreleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GarantieComponent,
        GarantieDetailComponent,
        GarantieUpdateComponent,
        GarantieDeleteDialogComponent,
        GarantieDeletePopupComponent
    ],
    entryComponents: [GarantieComponent, GarantieUpdateComponent, GarantieDeleteDialogComponent, GarantieDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GreleApplicationGarantieModule {}
