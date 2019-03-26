/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CultureComponentsPage, CultureDeleteDialog, CultureUpdatePage } from './culture.page-object';

const expect = chai.expect;

describe('Culture e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let cultureUpdatePage: CultureUpdatePage;
    let cultureComponentsPage: CultureComponentsPage;
    let cultureDeleteDialog: CultureDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Cultures', async () => {
        await navBarPage.goToEntity('culture');
        cultureComponentsPage = new CultureComponentsPage();
        await browser.wait(ec.visibilityOf(cultureComponentsPage.title), 5000);
        expect(await cultureComponentsPage.getTitle()).to.eq('Cultures');
    });

    it('should load create Culture page', async () => {
        await cultureComponentsPage.clickOnCreateButton();
        cultureUpdatePage = new CultureUpdatePage();
        expect(await cultureUpdatePage.getPageTitle()).to.eq('Create or edit a Culture');
        await cultureUpdatePage.cancel();
    });

    it('should create and save Cultures', async () => {
        const nbButtonsBeforeCreate = await cultureComponentsPage.countDeleteButtons();

        await cultureComponentsPage.clickOnCreateButton();
        await promise.all([
            cultureUpdatePage.setIdRisqueInput('5'),
            cultureUpdatePage.setIdCultureInput('5'),
            cultureUpdatePage.setNumOrdreInput('5'),
            cultureUpdatePage.setClasseNatureCLTInput('5'),
            cultureUpdatePage.setNatureCultureInput('natureCulture'),
            cultureUpdatePage.setCodeCaracteristiqueInput('codeCaracteristique'),
            cultureUpdatePage.setLibelleCaracteristiqueInput('libelleCaracteristique'),
            cultureUpdatePage.setPrixOfficielInput('5'),
            cultureUpdatePage.setUniteMesureInput('uniteMesure'),
            cultureUpdatePage.setSuperficieInput('5'),
            cultureUpdatePage.setSuperficieExplInput('5'),
            cultureUpdatePage.setUniteSuperficieInput('uniteSuperficie'),
            cultureUpdatePage.setNbParcellesInput('5'),
            cultureUpdatePage.setRendementMoyenInput('5'),
            cultureUpdatePage.setPrixMoyenInput('5'),
            cultureUpdatePage.setPrixAutreInput('5'),
            cultureUpdatePage.setPrixRetenuInput('5'),
            cultureUpdatePage.setRendementN1Input('5'),
            cultureUpdatePage.setRendementN2Input('5'),
            cultureUpdatePage.setRendementN3Input('5'),
            cultureUpdatePage.setRendementN4Input('5'),
            cultureUpdatePage.setRendementN5Input('5'),
            cultureUpdatePage.setPrixUnitaire1Input('5'),
            cultureUpdatePage.setPrixUnitaire2Input('5'),
            cultureUpdatePage.setPrixUnitaire3Input('5'),
            cultureUpdatePage.setPrixUnitaire4Input('5'),
            cultureUpdatePage.setPrixUnitaire5Input('5'),
            cultureUpdatePage.setMotifAutrePrixInput('motifAutrePrix')
        ]);
        expect(await cultureUpdatePage.getIdRisqueInput()).to.eq('5');
        expect(await cultureUpdatePage.getIdCultureInput()).to.eq('5');
        expect(await cultureUpdatePage.getNumOrdreInput()).to.eq('5');
        expect(await cultureUpdatePage.getClasseNatureCLTInput()).to.eq('5');
        expect(await cultureUpdatePage.getNatureCultureInput()).to.eq('natureCulture');
        expect(await cultureUpdatePage.getCodeCaracteristiqueInput()).to.eq('codeCaracteristique');
        expect(await cultureUpdatePage.getLibelleCaracteristiqueInput()).to.eq('libelleCaracteristique');
        expect(await cultureUpdatePage.getPrixOfficielInput()).to.eq('5');
        expect(await cultureUpdatePage.getUniteMesureInput()).to.eq('uniteMesure');
        expect(await cultureUpdatePage.getSuperficieInput()).to.eq('5');
        expect(await cultureUpdatePage.getSuperficieExplInput()).to.eq('5');
        expect(await cultureUpdatePage.getUniteSuperficieInput()).to.eq('uniteSuperficie');
        expect(await cultureUpdatePage.getNbParcellesInput()).to.eq('5');
        const selectedCultureirriguee = cultureUpdatePage.getCultureirrigueeInput();
        if (await selectedCultureirriguee.isSelected()) {
            await cultureUpdatePage.getCultureirrigueeInput().click();
            expect(await cultureUpdatePage.getCultureirrigueeInput().isSelected()).to.be.false;
        } else {
            await cultureUpdatePage.getCultureirrigueeInput().click();
            expect(await cultureUpdatePage.getCultureirrigueeInput().isSelected()).to.be.true;
        }
        expect(await cultureUpdatePage.getRendementMoyenInput()).to.eq('5');
        expect(await cultureUpdatePage.getPrixMoyenInput()).to.eq('5');
        expect(await cultureUpdatePage.getPrixAutreInput()).to.eq('5');
        expect(await cultureUpdatePage.getPrixRetenuInput()).to.eq('5');
        expect(await cultureUpdatePage.getRendementN1Input()).to.eq('5');
        expect(await cultureUpdatePage.getRendementN2Input()).to.eq('5');
        expect(await cultureUpdatePage.getRendementN3Input()).to.eq('5');
        expect(await cultureUpdatePage.getRendementN4Input()).to.eq('5');
        expect(await cultureUpdatePage.getRendementN5Input()).to.eq('5');
        expect(await cultureUpdatePage.getPrixUnitaire1Input()).to.eq('5');
        expect(await cultureUpdatePage.getPrixUnitaire2Input()).to.eq('5');
        expect(await cultureUpdatePage.getPrixUnitaire3Input()).to.eq('5');
        expect(await cultureUpdatePage.getPrixUnitaire4Input()).to.eq('5');
        expect(await cultureUpdatePage.getPrixUnitaire5Input()).to.eq('5');
        expect(await cultureUpdatePage.getMotifAutrePrixInput()).to.eq('motifAutrePrix');
        const selectedMajEnAttente = cultureUpdatePage.getMajEnAttenteInput();
        if (await selectedMajEnAttente.isSelected()) {
            await cultureUpdatePage.getMajEnAttenteInput().click();
            expect(await cultureUpdatePage.getMajEnAttenteInput().isSelected()).to.be.false;
        } else {
            await cultureUpdatePage.getMajEnAttenteInput().click();
            expect(await cultureUpdatePage.getMajEnAttenteInput().isSelected()).to.be.true;
        }
        const selectedAbsenceCultureAvenir = cultureUpdatePage.getAbsenceCultureAvenirInput();
        if (await selectedAbsenceCultureAvenir.isSelected()) {
            await cultureUpdatePage.getAbsenceCultureAvenirInput().click();
            expect(await cultureUpdatePage.getAbsenceCultureAvenirInput().isSelected()).to.be.false;
        } else {
            await cultureUpdatePage.getAbsenceCultureAvenirInput().click();
            expect(await cultureUpdatePage.getAbsenceCultureAvenirInput().isSelected()).to.be.true;
        }
        await cultureUpdatePage.save();
        expect(await cultureUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await cultureComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Culture', async () => {
        const nbButtonsBeforeDelete = await cultureComponentsPage.countDeleteButtons();
        await cultureComponentsPage.clickOnLastDeleteButton();

        cultureDeleteDialog = new CultureDeleteDialog();
        expect(await cultureDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Culture?');
        await cultureDeleteDialog.clickOnConfirmButton();

        expect(await cultureComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
