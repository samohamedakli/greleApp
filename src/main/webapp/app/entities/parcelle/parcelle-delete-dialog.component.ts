import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IParcelle } from 'app/shared/model/parcelle.model';
import { ParcelleService } from './parcelle.service';

@Component({
    selector: 'jhi-parcelle-delete-dialog',
    templateUrl: './parcelle-delete-dialog.component.html'
})
export class ParcelleDeleteDialogComponent {
    parcelle: IParcelle;

    constructor(protected parcelleService: ParcelleService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.parcelleService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'parcelleListModification',
                content: 'Deleted an parcelle'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-parcelle-delete-popup',
    template: ''
})
export class ParcelleDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ parcelle }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ParcelleDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.parcelle = parcelle;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/parcelle', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/parcelle', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
