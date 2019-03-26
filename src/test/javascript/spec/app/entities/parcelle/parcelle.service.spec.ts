/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { ParcelleService } from 'app/entities/parcelle/parcelle.service';
import { IParcelle, Parcelle } from 'app/shared/model/parcelle.model';

describe('Service Tests', () => {
    describe('Parcelle Service', () => {
        let injector: TestBed;
        let service: ParcelleService;
        let httpMock: HttpTestingController;
        let elemDefault: IParcelle;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ParcelleService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Parcelle(0, 0, 0, 0, 0, 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, 0);
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

            it('should create a Parcelle', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Parcelle(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Parcelle', async () => {
                const returnedFromService = Object.assign(
                    {
                        idRisque: 1,
                        idParcelle: 1,
                        numOrdre: 1,
                        codePostal: 1,
                        libelleCommune: 'BBBBBB',
                        lieudit: 'BBBBBB',
                        superficie: 1,
                        rendement: 1,
                        prixUnitaire: 1,
                        tauxCotisationGrele: 1,
                        tauxCotisationArc: 1
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

            it('should return a list of Parcelle', async () => {
                const returnedFromService = Object.assign(
                    {
                        idRisque: 1,
                        idParcelle: 1,
                        numOrdre: 1,
                        codePostal: 1,
                        libelleCommune: 'BBBBBB',
                        lieudit: 'BBBBBB',
                        superficie: 1,
                        rendement: 1,
                        prixUnitaire: 1,
                        tauxCotisationGrele: 1,
                        tauxCotisationArc: 1
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

            it('should delete a Parcelle', async () => {
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
