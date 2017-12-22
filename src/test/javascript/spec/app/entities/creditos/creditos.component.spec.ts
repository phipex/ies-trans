/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';
import { Headers } from '@angular/http';

import { GamestransactionTestModule } from '../../../test.module';
import { CreditosComponent } from '../../../../../../main/webapp/app/entities/creditos/creditos.component';
import { CreditosService } from '../../../../../../main/webapp/app/entities/creditos/creditos.service';
import { Creditos } from '../../../../../../main/webapp/app/entities/creditos/creditos.model';

describe('Component Tests', () => {

    describe('Creditos Management Component', () => {
        let comp: CreditosComponent;
        let fixture: ComponentFixture<CreditosComponent>;
        let service: CreditosService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [GamestransactionTestModule],
                declarations: [CreditosComponent],
                providers: [
                    CreditosService
                ]
            })
            .overrideTemplate(CreditosComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CreditosComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CreditosService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new Creditos(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.creditos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
