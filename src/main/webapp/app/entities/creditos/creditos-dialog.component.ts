import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Creditos } from './creditos.model';
import { CreditosPopupService } from './creditos-popup.service';
import { CreditosService } from './creditos.service';

@Component({
    selector: 'jhi-creditos-dialog',
    templateUrl: './creditos-dialog.component.html'
})
export class CreditosDialogComponent implements OnInit {

    creditos: Creditos;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private creditosService: CreditosService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.creditos.id !== undefined) {
            this.subscribeToSaveResponse(
                this.creditosService.update(this.creditos));
        } else {
            this.subscribeToSaveResponse(
                this.creditosService.create(this.creditos));
        }
    }

    private subscribeToSaveResponse(result: Observable<Creditos>) {
        result.subscribe((res: Creditos) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Creditos) {
        this.eventManager.broadcast({ name: 'creditosListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-creditos-popup',
    template: ''
})
export class CreditosPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private creditosPopupService: CreditosPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.creditosPopupService
                    .open(CreditosDialogComponent as Component, params['id']);
            } else {
                this.creditosPopupService
                    .open(CreditosDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
