/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';
import { Headers } from '@angular/http';

import { GamestransactionTestModule } from '../../../test.module';
import { PlayRequestComponent } from '../../../../../../main/webapp/app/entities/play-request/play-request.component';
import { PlayRequestService } from '../../../../../../main/webapp/app/entities/play-request/play-request.service';
import { PlayRequest } from '../../../../../../main/webapp/app/entities/play-request/play-request.model';

describe('Component Tests', () => {

    describe('PlayRequest Management Component', () => {
        let comp: PlayRequestComponent;
        let fixture: ComponentFixture<PlayRequestComponent>;
        let service: PlayRequestService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [GamestransactionTestModule],
                declarations: [PlayRequestComponent],
                providers: [
                    PlayRequestService
                ]
            })
            .overrideTemplate(PlayRequestComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PlayRequestComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlayRequestService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new PlayRequest(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.playRequests[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
