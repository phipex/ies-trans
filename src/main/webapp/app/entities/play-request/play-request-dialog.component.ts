import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { PlayRequest } from './play-request.model';
import { PlayRequestPopupService } from './play-request-popup.service';
import { PlayRequestService } from './play-request.service';

@Component({
    selector: 'jhi-play-request-dialog',
    templateUrl: './play-request-dialog.component.html'
})
export class PlayRequestDialogComponent implements OnInit {

    playRequest: PlayRequest;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private playRequestService: PlayRequestService,
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
        if (this.playRequest.id !== undefined) {
            this.subscribeToSaveResponse(
                this.playRequestService.update(this.playRequest));
        } else {
            this.subscribeToSaveResponse(
                this.playRequestService.create(this.playRequest));
        }
    }

    private subscribeToSaveResponse(result: Observable<PlayRequest>) {
        result.subscribe((res: PlayRequest) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: PlayRequest) {
        this.eventManager.broadcast({ name: 'playRequestListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-play-request-popup',
    template: ''
})
export class PlayRequestPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private playRequestPopupService: PlayRequestPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.playRequestPopupService
                    .open(PlayRequestDialogComponent as Component, params['id']);
            } else {
                this.playRequestPopupService
                    .open(PlayRequestDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
