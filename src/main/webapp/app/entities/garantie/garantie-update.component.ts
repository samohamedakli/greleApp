import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IGarantie } from 'app/shared/model/garantie.model';
import { GarantieService } from './garantie.service';
import { ICulture } from 'app/shared/model/culture.model';
import { CultureService } from 'app/entities/culture';

@Component({
    selector: 'jhi-garantie-update',
    templateUrl: './garantie-update.component.html'
})
export class GarantieUpdateComponent implements OnInit {
    garantie: IGarantie;
    isSaving: boolean;

    cultures: ICulture[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected garantieService: GarantieService,
        protected cultureService: CultureService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ garantie }) => {
            this.garantie = garantie;
        });
        this.cultureService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICulture[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICulture[]>) => response.body)
            )
            .subscribe((res: ICulture[]) => (this.cultures = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.garantie.id !== undefined) {
            this.subscribeToSaveResponse(this.garantieService.update(this.garantie));
        } else {
            this.subscribeToSaveResponse(this.garantieService.create(this.garantie));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IGarantie>>) {
        result.subscribe((res: HttpResponse<IGarantie>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
