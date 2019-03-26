/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { CultureService } from 'app/entities/culture/culture.service';
import { ICulture, Culture } from 'app/shared/model/culture.model';

describe('Service Tests', () => {
    describe('Culture Service', () => {
        let injector: TestBed;
        let service: CultureService;
        let httpMock: HttpTestingController;
        let elemDefault: ICulture;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(CultureService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Culture(
                0,
                0,
                0,
                0,
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                'AAAAAAA',
                0,
                0,
                'AAAAAAA',
                0,
                false,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                'AAAAAAA',
                false,
                false
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Culture', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Culture(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Culture', async () => {
                const returnedFromService = Object.assign(
                    {
                        idRisque: 1,
                        idCulture: 1,
                        numOrdre: 1,
                        classeNatureCLT: 1,
                        natureCulture: 'BBBBBB',
                        codeCaracteristique: 'BBBBBB',
                        libelleCaracteristique: 'BBBBBB',
                        prixOfficiel: 1,
                        uniteMesure: 'BBBBBB',
                        superficie: 1,
                        superficieExpl: 1,
                        uniteSuperficie: 'BBBBBB',
                        nbParcelles: 1,
                        cultureirriguee: true,
                        rendementMoyen: 1,
                        prixMoyen: 1,
                        prixAutre: 1,
                        prixRetenu: 1,
                        rendementN1: 1,
                        rendementN2: 1,
                        rendementN3: 1,
                        rendementN4: 1,
                        rendementN5: 1,
                        prixUnitaire1: 1,
                        prixUnitaire2: 1,
                        prixUnitaire3: 1,
                        prixUnitaire4: 1,
                        prixUnitaire5: 1,
                        motifAutrePrix: 'BBBBBB',
                        majEnAttente: true,
                        absenceCultureAvenir: true
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Culture', async () => {
                const returnedFromService = Object.assign(
                    {
                        idRisque: 1,
                        idCulture: 1,
                        numOrdre: 1,
                        classeNatureCLT: 1,
                        natureCulture: 'BBBBBB',
                        codeCaracteristique: 'BBBBBB',
                        libelleCaracteristique: 'BBBBBB',
                        prixOfficiel: 1,
                        uniteMesure: 'BBBBBB',
                        superficie: 1,
                        superficieExpl: 1,
                        uniteSuperficie: 'BBBBBB',
                        nbParcelles: 1,
                        cultureirriguee: true,
                        rendementMoyen: 1,
                        prixMoyen: 1,
                        prixAutre: 1,
                        prixRetenu: 1,
                        rendementN1: 1,
                        rendementN2: 1,
                        rendementN3: 1,
                        rendementN4: 1,
                        rendementN5: 1,
                        prixUnitaire1: 1,
                        prixUnitaire2: 1,
                        prixUnitaire3: 1,
                        prixUnitaire4: 1,
                        prixUnitaire5: 1,
                        motifAutrePrix: 'BBBBBB',
                        majEnAttente: true,
                        absenceCultureAvenir: true
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Culture', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
