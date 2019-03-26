import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IGarantie } from 'app/shared/model/garantie.model';
import { AccountService } from 'app/core';
import { GarantieService } from './garantie.service';

@Component({
    selector: 'jhi-garantie',
    templateUrl: './garantie.component.html'
})
export class GarantieComponent implements OnInit, OnDestroy {
    garanties: IGarantie[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected garantieService: GarantieService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.garantieService
            .query()
            .pipe(
                filter((res: HttpResponse<IGarantie[]>) => res.ok),
                map((res: HttpResponse<IGarantie[]>) => res.body)
            )
            .subscribe(
                (res: IGarantie[]) => {
                    this.garanties = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInGaranties();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IGarantie) {
        return item.id;
    }

    registerChangeInGaranties() {
        this.eventSubscriber = this.eventManager.subscribe('garantieListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
