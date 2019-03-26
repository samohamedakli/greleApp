/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GreleApplicationTestModule } from '../../../test.module';
import { ParcelleDeleteDialogComponent } from 'app/entities/parcelle/parcelle-delete-dialog.component';
import { ParcelleService } from 'app/entities/parcelle/parcelle.service';

describe('Component Tests', () => {
    describe('Parcelle Management Delete Component', () => {
        let comp: ParcelleDeleteDialogComponent;
        let fixture: ComponentFixture<ParcelleDeleteDialogComponent>;
        let service: ParcelleService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GreleApplicationTestModule],
                declarations: [ParcelleDeleteDialogComponent]
            })
                .overrideTemplate(ParcelleDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ParcelleDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParcelleService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
