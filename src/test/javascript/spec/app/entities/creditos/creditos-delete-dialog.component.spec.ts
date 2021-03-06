/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { GamestransactionTestModule } from '../../../test.module';
import { CreditosDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/creditos/creditos-delete-dialog.component';
import { CreditosService } from '../../../../../../main/webapp/app/entities/creditos/creditos.service';

describe('Component Tests', () => {

    describe('Creditos Management Delete Component', () => {
        let comp: CreditosDeleteDialogComponent;
        let fixture: ComponentFixture<CreditosDeleteDialogComponent>;
        let service: CreditosService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [GamestransactionTestModule],
                declarations: [CreditosDeleteDialogComponent],
                providers: [
                    CreditosService
                ]
            })
            .overrideTemplate(CreditosDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CreditosDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CreditosService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
