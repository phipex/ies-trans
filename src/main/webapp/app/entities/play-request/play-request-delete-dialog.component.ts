import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { PlayRequest } from './play-request.model';
import { PlayRequestPopupService } from './play-request-popup.service';
import { PlayRequestService } from './play-request.service';

@Component({
    selector: 'jhi-play-request-delete-dialog',
    templateUrl: './play-request-delete-dialog.component.html'
})
export class PlayRequestDeleteDialogComponent {

    playRequest: PlayRequest;

    constructor(
        private playRequestService: PlayRequestService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.playRequestService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'playRequestListModification',
                content: 'Deleted an playRequest'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-play-request-delete-popup',
    template: ''
})
export class PlayRequestDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private playRequestPopupService: PlayRequestPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.playRequestPopupService
                .open(PlayRequestDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
