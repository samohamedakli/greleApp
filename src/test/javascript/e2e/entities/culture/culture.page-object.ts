import { element, by, ElementFinder } from 'protractor';

export class CultureComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-culture div table .btn-danger'));
    title = element.all(by.css('jhi-culture div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getText();
    }
}

export class CultureUpdatePage {
    pageTitle = element(by.id('jhi-culture-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    idRisqueInput = element(by.id('field_idRisque'));
    idCultureInput = element(by.id('field_idCulture'));
    numOrdreInput = element(by.id('field_numOrdre'));
    classeNatureCLTInput = element(by.id('field_classeNatureCLT'));
    natureCultureInput = element(by.id('field_natureCulture'));
    codeCaracteristiqueInput = element(by.id('field_codeCaracteristique'));
    libelleCaracteristiqueInput = element(by.id('field_libelleCaracteristique'));
    prixOfficielInput = element(by.id('field_prixOfficiel'));
    uniteMesureInput = element(by.id('field_uniteMesure'));
    superficieInput = element(by.id('field_superficie'));
    superficieExplInput = element(by.id('field_superficieExpl'));
    uniteSuperficieInput = element(by.id('field_uniteSuperficie'));
    nbParcellesInput = element(by.id('field_nbParcelles'));
    cultureirrigueeInput = element(by.id('field_cultureirriguee'));
    rendementMoyenInput = element(by.id('field_rendementMoyen'));
    prixMoyenInput = element(by.id('field_prixMoyen'));
    prixAutreInput = element(by.id('field_prixAutre'));
    prixRetenuInput = element(by.id('field_prixRetenu'));
    rendementN1Input = element(by.id('field_rendementN1'));
    rendementN2Input = element(by.id('field_rendementN2'));
    rendementN3Input = element(by.id('field_rendementN3'));
    rendementN4Input = element(by.id('field_rendementN4'));
    rendementN5Input = element(by.id('field_rendementN5'));
    prixUnitaire1Input = element(by.id('field_prixUnitaire1'));
    prixUnitaire2Input = element(by.id('field_prixUnitaire2'));
    prixUnitaire3Input = element(by.id('field_prixUnitaire3'));
    prixUnitaire4Input = element(by.id('field_prixUnitaire4'));
    prixUnitaire5Input = element(by.id('field_prixUnitaire5'));
    motifAutrePrixInput = element(by.id('field_motifAutrePrix'));
    majEnAttenteInput = element(by.id('field_majEnAttente'));
    absenceCultureAvenirInput = element(by.id('field_absenceCultureAvenir'));

    async getPageTitle() {
        return this.pageTitle.getText();
    }

    async setIdRisqueInput(idRisque) {
        await this.idRisqueInput.sendKeys(idRisque);
    }

    async getIdRisqueInput() {
        return this.idRisqueInput.getAttribute('value');
    }

    async setIdCultureInput(idCulture) {
        await this.idCultureInput.sendKeys(idCulture);
    }

    async getIdCultureInput() {
        return this.idCultureInput.getAttribute('value');
    }

    async setNumOrdreInput(numOrdre) {
        await this.numOrdreInput.sendKeys(numOrdre);
    }

    async getNumOrdreInput() {
        return this.numOrdreInput.getAttribute('value');
    }

    async setClasseNatureCLTInput(classeNatureCLT) {
        await this.classeNatureCLTInput.sendKeys(classeNatureCLT);
    }

    async getClasseNatureCLTInput() {
        return this.classeNatureCLTInput.getAttribute('value');
    }

    async setNatureCultureInput(natureCulture) {
        await this.natureCultureInput.sendKeys(natureCulture);
    }

    async getNatureCultureInput() {
        return this.natureCultureInput.getAttribute('value');
    }

    async setCodeCaracteristiqueInput(codeCaracteristique) {
        await this.codeCaracteristiqueInput.sendKeys(codeCaracteristique);
    }

    async getCodeCaracteristiqueInput() {
        return this.codeCaracteristiqueInput.getAttribute('value');
    }

    async setLibelleCaracteristiqueInput(libelleCaracteristique) {
        await this.libelleCaracteristiqueInput.sendKeys(libelleCaracteristique);
    }

    async getLibelleCaracteristiqueInput() {
        return this.libelleCaracteristiqueInput.getAttribute('value');
    }

    async setPrixOfficielInput(prixOfficiel) {
        await this.prixOfficielInput.sendKeys(prixOfficiel);
    }

    async getPrixOfficielInput() {
        return this.prixOfficielInput.getAttribute('value');
    }

    async setUniteMesureInput(uniteMesure) {
        await this.uniteMesureInput.sendKeys(uniteMesure);
    }

    async getUniteMesureInput() {
        return this.uniteMesureInput.getAttribute('value');
    }

    async setSuperficieInput(superficie) {
        await this.superficieInput.sendKeys(superficie);
    }

    async getSuperficieInput() {
        return this.superficieInput.getAttribute('value');
    }

    async setSuperficieExplInput(superficieExpl) {
        await this.superficieExplInput.sendKeys(superficieExpl);
    }

    async getSuperficieExplInput() {
        return this.superficieExplInput.getAttribute('value');
    }

    async setUniteSuperficieInput(uniteSuperficie) {
        await this.uniteSuperficieInput.sendKeys(uniteSuperficie);
    }

    async getUniteSuperficieInput() {
        return this.uniteSuperficieInput.getAttribute('value');
    }

    async setNbParcellesInput(nbParcelles) {
        await this.nbParcellesInput.sendKeys(nbParcelles);
    }

    async getNbParcellesInput() {
        return this.nbParcellesInput.getAttribute('value');
    }

    getCultureirrigueeInput() {
        return this.cultureirrigueeInput;
    }
    async setRendementMoyenInput(rendementMoyen) {
        await this.rendementMoyenInput.sendKeys(rendementMoyen);
    }

    async getRendementMoyenInput() {
        return this.rendementMoyenInput.getAttribute('value');
    }

    async setPrixMoyenInput(prixMoyen) {
        await this.prixMoyenInput.sendKeys(prixMoyen);
    }

    async getPrixMoyenInput() {
        return this.prixMoyenInput.getAttribute('value');
    }

    async setPrixAutreInput(prixAutre) {
        await this.prixAutreInput.sendKeys(prixAutre);
    }

    async getPrixAutreInput() {
        return this.prixAutreInput.getAttribute('value');
    }

    async setPrixRetenuInput(prixRetenu) {
        await this.prixRetenuInput.sendKeys(prixRetenu);
    }

    async getPrixRetenuInput() {
        return this.prixRetenuInput.getAttribute('value');
    }

    async setRendementN1Input(rendementN1) {
        await this.rendementN1Input.sendKeys(rendementN1);
    }

    async getRendementN1Input() {
        return this.rendementN1Input.getAttribute('value');
    }

    async setRendementN2Input(rendementN2) {
        await this.rendementN2Input.sendKeys(rendementN2);
    }

    async getRendementN2Input() {
        return this.rendementN2Input.getAttribute('value');
    }

    async setRendementN3Input(rendementN3) {
        await this.rendementN3Input.sendKeys(rendementN3);
    }

    async getRendementN3Input() {
        return this.rendementN3Input.getAttribute('value');
    }

    async setRendementN4Input(rendementN4) {
        await this.rendementN4Input.sendKeys(rendementN4);
    }

    async getRendementN4Input() {
        return this.rendementN4Input.getAttribute('value');
    }

    async setRendementN5Input(rendementN5) {
        await this.rendementN5Input.sendKeys(rendementN5);
    }

    async getRendementN5Input() {
        return this.rendementN5Input.getAttribute('value');
    }

    async setPrixUnitaire1Input(prixUnitaire1) {
        await this.prixUnitaire1Input.sendKeys(prixUnitaire1);
    }

    async getPrixUnitaire1Input() {
        return this.prixUnitaire1Input.getAttribute('value');
    }

    async setPrixUnitaire2Input(prixUnitaire2) {
        await this.prixUnitaire2Input.sendKeys(prixUnitaire2);
    }

    async getPrixUnitaire2Input() {
        return this.prixUnitaire2Input.getAttribute('value');
    }

    async setPrixUnitaire3Input(prixUnitaire3) {
        await this.prixUnitaire3Input.sendKeys(prixUnitaire3);
    }

    async getPrixUnitaire3Input() {
        return this.prixUnitaire3Input.getAttribute('value');
    }

    async setPrixUnitaire4Input(prixUnitaire4) {
        await this.prixUnitaire4Input.sendKeys(prixUnitaire4);
    }

    async getPrixUnitaire4Input() {
        return this.prixUnitaire4Input.getAttribute('value');
    }

    async setPrixUnitaire5Input(prixUnitaire5) {
        await this.prixUnitaire5Input.sendKeys(prixUnitaire5);
    }

    async getPrixUnitaire5Input() {
        return this.prixUnitaire5Input.getAttribute('value');
    }

    async setMotifAutrePrixInput(motifAutrePrix) {
        await this.motifAutrePrixInput.sendKeys(motifAutrePrix);
    }

    async getMotifAutrePrixInput() {
        return this.motifAutrePrixInput.getAttribute('value');
    }

    getMajEnAttenteInput() {
        return this.majEnAttenteInput;
    }
    getAbsenceCultureAvenirInput() {
        return this.absenceCultureAvenirInput;
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class CultureDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-culture-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-culture'));

    async getDialogTitle() {
        return this.dialogTitle.getText();
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
