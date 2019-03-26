import { ICulture } from 'app/shared/model/culture.model';

export interface IGarantie {
    id?: number;
    codeGarantie?: number;
    libelleGarantie?: string;
    typeFranchise?: string;
    tauxFranchise?: number;
    culture?: ICulture;
}

export class Garantie implements IGarantie {
    constructor(
        public id?: number,
        public codeGarantie?: number,
        public libelleGarantie?: string,
        public typeFranchise?: string,
        public tauxFranchise?: number,
        public culture?: ICulture
    ) {}
}
