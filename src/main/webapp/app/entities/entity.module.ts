import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'localisation',
                loadChildren: './localisation/localisation.module#GreleApplicationLocalisationModule'
            },
            {
                path: 'culture',
                loadChildren: './culture/culture.module#GreleApplicationCultureModule'
            },
            {
                path: 'parcelle',
                loadChildren: './parcelle/parcelle.module#GreleApplicationParcelleModule'
            },
            {
                path: 'garantie',
                loadChildren: './garantie/garantie.module#GreleApplicationGarantieModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GreleApplicationEntityModule {}
