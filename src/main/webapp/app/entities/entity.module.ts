import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { GamestransactionPlayRequestModule } from './play-request/play-request.module';
import { GamestransactionCreditosModule } from './creditos/creditos.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        GamestransactionPlayRequestModule,
        GamestransactionCreditosModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GamestransactionEntityModule {}
