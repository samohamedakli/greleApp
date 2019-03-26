import { IParcelle } from 'app/shared/model/parcelle.model';
import { IGarantie } from 'app/shared/model/garantie.model';

export interface ICulture {
    id?: number;
    idRisque?: number;
    idCulture?: number;
    numOrdre?: number;
    classeNatureCLT?: number;
    natureCulture?: string;
    codeCaracteristique?: string;
    libelleCaracteristique?: string;
    prixOfficiel?: number;
    uniteMesure?: string;
    superficie?: number;
    superficieExpl?: number;
    uniteSuperficie?: string;
    nbParcelles?: number;
    cultureirriguee?: boolean;
    rendementMoyen?: number;
    prixMoyen?: number;
    prixAutre?: number;
    prixRetenu?: number;
    rendementN1?: number;
    rendementN2?: number;
    rendementN3?: number;
    rendementN4?: number;
    rendementN5?: number;
    prixUnitaire1?: number;
    prixUnitaire2?: number;
    prixUnitaire3?: number;
    prixUnitaire4?: number;
    prixUnitaire5?: number;
    motifAutrePrix?: string;
    majEnAttente?: boolean;
    absenceCultureAvenir?: boolean;
    parcelles?: IParcelle[];
    garanties?: IGarantie[];
}

export class Culture implements ICulture {
    constructor(
        public id?: number,
        public idRisque?: number,
        public idCulture?: number,
        public numOrdre?: number,
        public classeNatureCLT?: number,
        public natureCulture?: string,
        public codeCaracteristique?: string,
        public libelleCaracteristique?: string,
        public prixOfficiel?: number,
        public uniteMesure?: string,
        public superficie?: number,
        public superficieExpl?: number,
        public uniteSuperficie?: string,
        public nbParcelles?: number,
        public cultureirriguee?: boolean,
        public rendementMoyen?: number,
        public prixMoyen?: number,
        public prixAutre?: number,
        public prixRetenu?: number,
        public rendementN1?: number,
        public rendementN2?: number,
        public rendementN3?: number,
        public rendementN4?: number,
        public rendementN5?: number,
        public prixUnitaire1?: number,
        public prixUnitaire2?: number,
        public prixUnitaire3?: number,
        public prixUnitaire4?: number,
        public prixUnitaire5?: number,
        public motifAutrePrix?: string,
        public majEnAttente?: boolean,
        public absenceCultureAvenir?: boolean,
        public parcelles?: IParcelle[],
        public garanties?: IGarantie[]
    ) {
        this.cultureirriguee = this.cultureirriguee || false;
        this.majEnAttente = this.majEnAttente || false;
        this.absenceCultureAvenir = this.absenceCultureAvenir || false;
    }
}
