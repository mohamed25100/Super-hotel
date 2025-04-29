import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCityComponent } from './add-city.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';

describe('AddCityComponent', () => {
  let component: AddCityComponent;
  let fixture: ComponentFixture<AddCityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule,
        FormsModule
      ],
      declarations: [ AddCityComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddCityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
