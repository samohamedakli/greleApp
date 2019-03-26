import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGarantie } from 'app/shared/model/garantie.model';

@Component({
    selector: 'jhi-garantie-detail',
    templateUrl: './garantie-detail.component.html'
})
export class GarantieDetailComponent implements OnInit {
    garantie: IGarantie;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ garantie }) => {
            this.garantie = garantie;
        });
    }

    previousState() {
        window.history.back();
    }
}
