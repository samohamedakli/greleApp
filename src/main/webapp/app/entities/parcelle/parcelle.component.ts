import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IParcelle } from 'app/shared/model/parcelle.model';
import { AccountService } from 'app/core';
import { ParcelleService } from './parcelle.service';

@Component({
    selector: 'jhi-parcelle',
    templateUrl: './parcelle.component.html'
})
export class ParcelleComponent implements OnInit, OnDestroy {
    parcelles: IParcelle[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected parcelleService: ParcelleService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.parcelleService
            .query()
            .pipe(
                filter((res: HttpResponse<IParcelle[]>) => res.ok),
                map((res: HttpResponse<IParcelle[]>) => res.body)
            )
            .subscribe(
                (res: IParcelle[]) => {
                    this.parcelles = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInParcelles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IParcelle) {
        return item.id;
    }

    registerChangeInParcelles() {
        this.eventSubscriber = this.eventManager.subscribe('parcelleListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
