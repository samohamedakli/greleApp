export interface ILocalisation {
    id?: number;
    address?: string;
    codePostal?: string;
    ville?: string;
    lieuDit?: string;
}

export class Localisation implements ILocalisation {
    constructor(public id?: number, public address?: string, public codePostal?: string, public ville?: string, public lieuDit?: string) {}
}
