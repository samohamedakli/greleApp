import { ICulture } from 'app/shared/model/culture.model';
import { ILocalisation } from 'app/shared/model/localisation.model';

export interface IParcelle {
    id?: number;
    idRisque?: number;
    idParcelle?: number;
    numOrdre?: number;
    codePostal?: number;
    libelleCommune?: string;
    lieudit?: string;
    superficie?: number;
    rendement?: number;
    prixUnitaire?: number;
    tauxCotisationGrele?: number;
    tauxCotisationArc?: number;
    culture?: ICulture;
    localisation?: ILocalisation;
}

export class Parcelle implements IParcelle {
    constructor(
        public id?: number,
        public idRisque?: number,
        public idParcelle?: number,
        public numOrdre?: number,
        public codePostal?: number,
        public libelleCommune?: string,
        public lieudit?: string,
        public superficie?: number,
        public rendement?: number,
        public prixUnitaire?: number,
        public tauxCotisationGrele?: number,
        public tauxCotisationArc?: number,
        public culture?: ICulture,
        public localisation?: ILocalisation
    ) {}
}
