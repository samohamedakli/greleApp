import { element, by, ElementFinder } from 'protractor';

export class GarantieComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-garantie div table .btn-danger'));
    title = element.all(by.css('jhi-garantie div h2#page-heading span')).first();

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

export class GarantieUpdatePage {
    pageTitle = element(by.id('jhi-garantie-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    codeGarantieInput = element(by.id('field_codeGarantie'));
    libelleGarantieInput = element(by.id('field_libelleGarantie'));
    typeFranchiseInput = element(by.id('field_typeFranchise'));
    tauxFranchiseInput = element(by.id('field_tauxFranchise'));
    cultureSelect = element(by.id('field_culture'));

    async getPageTitle() {
        return this.pageTitle.getText();
    }

    async setCodeGarantieInput(codeGarantie) {
        await this.codeGarantieInput.sendKeys(codeGarantie);
    }

    async getCodeGarantieInput() {
        return this.codeGarantieInput.getAttribute('value');
    }

    async setLibelleGarantieInput(libelleGarantie) {
        await this.libelleGarantieInput.sendKeys(libelleGarantie);
    }

    async getLibelleGarantieInput() {
        return this.libelleGarantieInput.getAttribute('value');
    }

    async setTypeFranchiseInput(typeFranchise) {
        await this.typeFranchiseInput.sendKeys(typeFranchise);
    }

    async getTypeFranchiseInput() {
        return this.typeFranchiseInput.getAttribute('value');
    }

    async setTauxFranchiseInput(tauxFranchise) {
        await this.tauxFranchiseInput.sendKeys(tauxFranchise);
    }

    async getTauxFranchiseInput() {
        return this.tauxFranchiseInput.getAttribute('value');
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

export class GarantieDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-garantie-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-garantie'));

    async getDialogTitle() {
        return this.dialogTitle.getText();
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
