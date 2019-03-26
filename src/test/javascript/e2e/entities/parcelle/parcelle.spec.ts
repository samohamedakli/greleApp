/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ParcelleComponentsPage, ParcelleDeleteDialog, ParcelleUpdatePage } from './parcelle.page-object';

const expect = chai.expect;

describe('Parcelle e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let parcelleUpdatePage: ParcelleUpdatePage;
    let parcelleComponentsPage: ParcelleComponentsPage;
    let parcelleDeleteDialog: ParcelleDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Parcelles', async () => {
        await navBarPage.goToEntity('parcelle');
        parcelleComponentsPage = new ParcelleComponentsPage();
        await browser.wait(ec.visibilityOf(parcelleComponentsPage.title), 5000);
        expect(await parcelleComponentsPage.getTitle()).to.eq('Parcelles');
    });

    it('should load create Parcelle page', async () => {
        await parcelleComponentsPage.clickOnCreateButton();
        parcelleUpdatePage = new ParcelleUpdatePage();
        expect(await parcelleUpdatePage.getPageTitle()).to.eq('Create or edit a Parcelle');
        await parcelleUpdatePage.cancel();
    });

    it('should create and save Parcelles', async () => {
        const nbButtonsBeforeCreate = await parcelleComponentsPage.countDeleteButtons();

        await parcelleComponentsPage.clickOnCreateButton();
        await promise.all([
            parcelleUpdatePage.setIdRisqueInput('5'),
            parcelleUpdatePage.setIdParcelleInput('5'),
            parcelleUpdatePage.setNumOrdreInput('5'),
            parcelleUpdatePage.setCodePostalInput('5'),
            parcelleUpdatePage.setLibelleCommuneInput('libelleCommune'),
            parcelleUpdatePage.setLieuditInput('lieudit'),
            parcelleUpdatePage.setSuperficieInput('5'),
            parcelleUpdatePage.setRendementInput('5'),
            parcelleUpdatePage.setPrixUnitaireInput('5'),
            parcelleUpdatePage.setTauxCotisationGreleInput('5'),
            parcelleUpdatePage.setTauxCotisationArcInput('5'),
            parcelleUpdatePage.cultureSelectLastOption(),
            parcelleUpdatePage.localisationSelectLastOption()
        ]);
        expect(await parcelleUpdatePage.getIdRisqueInput()).to.eq('5');
        expect(await parcelleUpdatePage.getIdParcelleInput()).to.eq('5');
        expect(await parcelleUpdatePage.getNumOrdreInput()).to.eq('5');
        expect(await parcelleUpdatePage.getCodePostalInput()).to.eq('5');
        expect(await parcelleUpdatePage.getLibelleCommuneInput()).to.eq('libelleCommune');
        expect(await parcelleUpdatePage.getLieuditInput()).to.eq('lieudit');
        expect(await parcelleUpdatePage.getSuperficieInput()).to.eq('5');
        expect(await parcelleUpdatePage.getRendementInput()).to.eq('5');
        expect(await parcelleUpdatePage.getPrixUnitaireInput()).to.eq('5');
        expect(await parcelleUpdatePage.getTauxCotisationGreleInput()).to.eq('5');
        expect(await parcelleUpdatePage.getTauxCotisationArcInput()).to.eq('5');
        await parcelleUpdatePage.save();
        expect(await parcelleUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await parcelleComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Parcelle', async () => {
        const nbButtonsBeforeDelete = await parcelleComponentsPage.countDeleteButtons();
        await parcelleComponentsPage.clickOnLastDeleteButton();

        parcelleDeleteDialog = new ParcelleDeleteDialog();
        expect(await parcelleDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Parcelle?');
        await parcelleDeleteDialog.clickOnConfirmButton();

        expect(await parcelleComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
