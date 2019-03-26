import { NgModule } from '@angular/core';

import { GreleApplicationSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [GreleApplicationSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [GreleApplicationSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class GreleApplicationSharedCommonModule {}
