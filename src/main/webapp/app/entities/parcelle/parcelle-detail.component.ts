import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IParcelle } from 'app/shared/model/parcelle.model';

@Component({
    selector: 'jhi-parcelle-detail',
    templateUrl: './parcelle-detail.component.html'
})
export class ParcelleDetailComponent implements OnInit {
    parcelle: IParcelle;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ parcelle }) => {
            this.parcelle = parcelle;
        });
    }

    previousState() {
        window.history.back();
    }
}
