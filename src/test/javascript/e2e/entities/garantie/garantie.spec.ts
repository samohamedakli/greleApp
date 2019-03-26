/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GarantieComponentsPage, GarantieDeleteDialog, GarantieUpdatePage } from './garantie.page-object';

const expect = chai.expect;

describe('Garantie e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let garantieUpdatePage: GarantieUpdatePage;
    let garantieComponentsPage: GarantieComponentsPage;
    let garantieDeleteDialog: GarantieDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Garanties', async () => {
        await navBarPage.goToEntity('garantie');
        garantieComponentsPage = new GarantieComponentsPage();
        await browser.wait(ec.visibilityOf(garantieComponentsPage.title), 5000);
        expect(await garantieComponentsPage.getTitle()).to.eq('Garanties');
    });

    it('should load create Garantie page', async () => {
        await garantieComponentsPage.clickOnCreateButton();
        garantieUpdatePage = new GarantieUpdatePage();
        expect(await garantieUpdatePage.getPageTitle()).to.eq('Create or edit a Garantie');
        await garantieUpdatePage.cancel();
    });

    it('should create and save Garanties', async () => {
        const nbButtonsBeforeCreate = await garantieComponentsPage.countDeleteButtons();

        await garantieComponentsPage.clickOnCreateButton();
        await promise.all([
            garantieUpdatePage.setCodeGarantieInput('5'),
            garantieUpdatePage.setLibelleGarantieInput('libelleGarantie'),
            garantieUpdatePage.setTypeFranchiseInput('typeFranchise'),
            garantieUpdatePage.setTauxFranchiseInput('5'),
            garantieUpdatePage.cultureSelectLastOption()
        ]);
        expect(await garantieUpdatePage.getCodeGarantieInput()).to.eq('5');
        expect(await garantieUpdatePage.getLibelleGarantieInput()).to.eq('libelleGarantie');
        expect(await garantieUpdatePage.getTypeFranchiseInput()).to.eq('typeFranchise');
        expect(await garantieUpdatePage.getTauxFranchiseInput()).to.eq('5');
        await garantieUpdatePage.save();
        expect(await garantieUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await garantieComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Garantie', async () => {
        const nbButtonsBeforeDelete = await garantieComponentsPage.countDeleteButtons();
        await garantieComponentsPage.clickOnLastDeleteButton();

        garantieDeleteDialog = new GarantieDeleteDialog();
        expect(await garantieDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Garantie?');
        await garantieDeleteDialog.clickOnConfirmButton();

        expect(await garantieComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
