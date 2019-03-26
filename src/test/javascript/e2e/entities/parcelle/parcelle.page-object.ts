import { element, by, ElementFinder } from 'protractor';

export class ParcelleComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-parcelle div table .btn-danger'));
    title = element.all(by.css('jhi-parcelle div h2#page-heading span')).first();

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

export class ParcelleUpdatePage {
    pageTitle = element(by.id('jhi-parcelle-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    idRisqueInput = element(by.id('field_idRisque'));
    idParcelleInput = element(by.id('field_idParcelle'));
    numOrdreInput = element(by.id('field_numOrdre'));
    codePostalInput = element(by.id('field_codePostal'));
    libelleCommuneInput = element(by.id('field_libelleCommune'));
    lieuditInput = element(by.id('field_lieudit'));
    superficieInput = element(by.id('field_superficie'));
    rendementInput = element(by.id('field_rendement'));
    prixUnitaireInput = element(by.id('field_prixUnitaire'));
    tauxCotisationGreleInput = element(by.id('field_tauxCotisationGrele'));
    tauxCotisationArcInput = element(by.id('field_tauxCotisationArc'));
    cultureSelect = element(by.id('field_culture'));
    localisationSelect = element(by.id('field_localisation'));

    async getPageTitle() {
        return this.pageTitle.getText();
    }

    async setIdRisqueInput(idRisque) {
        await this.idRisqueInput.sendKeys(idRisque);
    }

    async getIdRisqueInput() {
        return this.idRisqueInput.getAttribute('value');
    }

    async setIdParcelleInput(idParcelle) {
        await this.idParcelleInput.sendKeys(idParcelle);
    }

    async getIdParcelleInput() {
        return this.idParcelleInput.getAttribute('value');
    }

    async setNumOrdreInput(numOrdre) {
        await this.numOrdreInput.sendKeys(numOrdre);
    }

    async getNumOrdreInput() {
        return this.numOrdreInput.getAttribute('value');
    }

    async setCodePostalInput(codePostal) {
        await this.codePostalInput.sendKeys(codePostal);
    }

    async getCodePostalInput() {
        return this.codePostalInput.getAttribute('value');
    }

    async setLibelleCommuneInput(libelleCommune) {
        await this.libelleCommuneInput.sendKeys(libelleCommune);
    }

    async getLibelleCommuneInput() {
        return this.libelleCommuneInput.getAttribute('value');
    }

    async setLieuditInput(lieudit) {
        await this.lieuditInput.sendKeys(lieudit);
    }

    async getLieuditInput() {
        return this.lieuditInput.getAttribute('value');
    }

    async setSuperficieInput(superficie) {
        await this.superficieInput.sendKeys(superficie);
    }

    async getSuperficieInput() {
        return this.superficieInput.getAttribute('value');
    }

    async setRendementInput(rendement) {
        await this.rendementInput.sendKeys(rendement);
    }

    async getRendementInput() {
        return this.rendementInput.getAttribute('value');
    }

    async setPrixUnitaireInput(prixUnitaire) {
        await this.prixUnitaireInput.sendKeys(prixUnitaire);
    }

    async getPrixUnitaireInput() {
        return this.prixUnitaireInput.getAttribute('value');
    }

    async setTauxCotisationGreleInput(tauxCotisationGrele) {
        await this.tauxCotisationGreleInput.sendKeys(tauxCotisationGrele);
    }

    async getTauxCotisationGreleInput() {
        return this.tauxCotisationGreleInput.getAttribute('value');
    }

    async setTauxCotisationArcInput(tauxCotisationArc) {
        await this.tauxCotisationArcInput.sendKeys(tauxCotisationArc);
    }

    async getTauxCotisationArcInput() {
        return this.tauxCotisationArcInput.getAttribute('value');
    }

    async cultureSelectLastOption() {
        await this.cultureSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async cultureSelectOption(option) {
        await this.cultureSelect.sendKeys(option);
    }

    getCultureSelect(): ElementFinder {
        return this.cultureSelect;
    }

    async getCultureSelectedOption() {
        return this.cultureSelect.element(by.css('option:checked')).getText();
    }

    async localisationSelectLastOption() {
        await this.localisationSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async localisationSelectOption(option) {
        await this.localisationSelect.sendKeys(option);
    }

    getLocalisationSelect(): ElementFinder {
        return this.localisationSelect;
    }

    async getLocalisationSelectedOption() {
        return this.localisationSelect.element(by.css('option:checked')).getText();
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

export class ParcelleDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-parcelle-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-parcelle'));

    async getDialogTitle() {
        return this.dialogTitle.getText();
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
