import { element, by, ElementFinder } from 'protractor';

export class LocalisationComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-localisation div table .btn-danger'));
    title = element.all(by.css('jhi-localisation div h2#page-heading span')).first();

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

export class LocalisationUpdatePage {
    pageTitle = element(by.id('jhi-localisation-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    addressInput = element(by.id('field_address'));
    codePostalInput = element(by.id('field_codePostal'));
    villeInput = element(by.id('field_ville'));
    lieuDitInput = element(by.id('field_lieuDit'));

    async getPageTitle() {
        return this.pageTitle.getText();
    }

    async setAddressInput(address) {
        await this.addressInput.sendKeys(address);
    }

    async getAddressInput() {
        return this.addressInput.getAttribute('value');
    }

    async setCodePostalInput(codePostal) {
        await this.codePostalInput.sendKeys(codePostal);
    }

    async getCodePostalInput() {
        return this.codePostalInput.getAttribute('value');
    }

    async setVilleInput(ville) {
        await this.villeInput.sendKeys(ville);
    }

    async getVilleInput() {
        return this.villeInput.getAttribute('value');
    }

    async setLieuDitInput(lieuDit) {
        await this.lieuDitInput.sendKeys(lieuDit);
    }

    async getLieuDitInput() {
        return this.lieuDitInput.getAttribute('value');
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

export class LocalisationDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-localisation-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-localisation'));

    async getDialogTitle() {
        return this.dialogTitle.getText();
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
