import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Creditos } from './creditos.model';
import { CreditosPopupService } from './creditos-popup.service';
import { CreditosService } from './creditos.service';

@Component({
    selector: 'jhi-creditos-delete-dialog',
    templateUrl: './creditos-delete-dialog.component.html'
})
export class CreditosDeleteDialogComponent {

    creditos: Creditos;

    constructor(
        private creditosService: CreditosService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.creditosService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'creditosListModification',
                content: 'Deleted an creditos'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-creditos-delete-popup',
    template: ''
})
export class CreditosDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private creditosPopupService: CreditosPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.creditosPopupService
                .open(CreditosDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
