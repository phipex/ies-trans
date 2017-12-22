import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GamestransactionSharedModule } from '../../shared';
import {
    CreditosService,
    CreditosPopupService,
    CreditosComponent,
    CreditosDetailComponent,
    CreditosDialogComponent,
    CreditosPopupComponent,
    CreditosDeletePopupComponent,
    CreditosDeleteDialogComponent,
    creditosRoute,
    creditosPopupRoute,
} from './';

const ENTITY_STATES = [
    ...creditosRoute,
    ...creditosPopupRoute,
];

@NgModule({
    imports: [
        GamestransactionSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CreditosComponent,
        CreditosDetailComponent,
        CreditosDialogComponent,
        CreditosDeleteDialogComponent,
        CreditosPopupComponent,
        CreditosDeletePopupComponent,
    ],
    entryComponents: [
        CreditosComponent,
        CreditosDialogComponent,
        CreditosPopupComponent,
        CreditosDeleteDialogComponent,
        CreditosDeletePopupComponent,
    ],
    providers: [
        CreditosService,
        CreditosPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GamestransactionCreditosModule {}
