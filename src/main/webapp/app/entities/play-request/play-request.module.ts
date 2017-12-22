import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GamestransactionSharedModule } from '../../shared';
import {
    PlayRequestService,
    PlayRequestPopupService,
    PlayRequestComponent,
    PlayRequestDetailComponent,
    PlayRequestDialogComponent,
    PlayRequestPopupComponent,
    PlayRequestDeletePopupComponent,
    PlayRequestDeleteDialogComponent,
    playRequestRoute,
    playRequestPopupRoute,
    PlayRequestResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...playRequestRoute,
    ...playRequestPopupRoute,
];

@NgModule({
    imports: [
        GamestransactionSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        PlayRequestComponent,
        PlayRequestDetailComponent,
        PlayRequestDialogComponent,
        PlayRequestDeleteDialogComponent,
        PlayRequestPopupComponent,
        PlayRequestDeletePopupComponent,
    ],
    entryComponents: [
        PlayRequestComponent,
        PlayRequestDialogComponent,
        PlayRequestPopupComponent,
        PlayRequestDeleteDialogComponent,
        PlayRequestDeletePopupComponent,
    ],
    providers: [
        PlayRequestService,
        PlayRequestPopupService,
        PlayRequestResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GamestransactionPlayRequestModule {}
