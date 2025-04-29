import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelsComponent } from './hotels.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';

describe('HotelsComponent', () => {
  let component: HotelsComponent;
  let fixture: ComponentFixture<HotelsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule
      ],
      declarations: [ HotelsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HotelsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
