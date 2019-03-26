/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GreleApplicationTestModule } from '../../../test.module';
import { ParcelleComponent } from 'app/entities/parcelle/parcelle.component';
import { ParcelleService } from 'app/entities/parcelle/parcelle.service';
import { Parcelle } from 'app/shared/model/parcelle.model';

describe('Component Tests', () => {
    describe('Parcelle Management Component', () => {
        let comp: ParcelleComponent;
        let fixture: ComponentFixture<ParcelleComponent>;
        let service: ParcelleService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GreleApplicationTestModule],
                declarations: [ParcelleComponent],
                providers: []
            })
                .overrideTemplate(ParcelleComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ParcelleComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParcelleService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Parcelle(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.parcelles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
