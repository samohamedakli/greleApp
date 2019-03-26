import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IParcelle } from 'app/shared/model/parcelle.model';
import { ParcelleService } from './parcelle.service';
import { ICulture } from 'app/shared/model/culture.model';
import { CultureService } from 'app/entities/culture';
import { ILocalisation } from 'app/shared/model/localisation.model';
import { LocalisationService } from 'app/entities/localisation';

@Component({
    selector: 'jhi-parcelle-update',
    templateUrl: './parcelle-update.component.html'
})
export class ParcelleUpdateComponent implements OnInit {
    parcelle: IParcelle;
    isSaving: boolean;

    cultures: ICulture[];

    localisations: ILocalisation[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected parcelleService: ParcelleService,
        protected cultureService: CultureService,
        protected localisationService: LocalisationService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ parcelle }) => {
            this.parcelle = parcelle;
        });
        this.cultureService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICulture[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICulture[]>) => response.body)
            )
            .subscribe((res: ICulture[]) => (this.cultures = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.localisationService
            .query({ filter: 'parcelle-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<ILocalisation[]>) => mayBeOk.ok),
                map((response: HttpResponse<ILocalisation[]>) => response.body)
            )
            .subscribe(
                (res: ILocalisation[]) => {
                    if (!this.parcelle.localisation || !this.parcelle.localisation.id) {
                        this.localisations = res;
                    } else {
                        this.localisationService
                            .find(this.parcelle.localisation.id)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<ILocalisation>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<ILocalisation>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: ILocalisation) => (this.localisations = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.parcelle.id !== undefined) {
            this.subscribeToSaveResponse(this.parcelleService.update(this.parcelle));
        } else {
            this.subscribeToSaveResponse(this.parcelleService.create(this.parcelle));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IParcelle>>) {
        result.subscribe((res: HttpResponse<IParcelle>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCultureById(index: number, item: ICulture) {
        return item.id;
    }

    trackLocalisationById(index: number, item: ILocalisation) {
        return item.id;
    }
}
