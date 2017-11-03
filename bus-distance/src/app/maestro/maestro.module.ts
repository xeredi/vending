import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from '../shared/shared.module';

import { MaestroRoutingModule } from './maestro-routing.module';

import { PlacaService } from './placa.service';
import { RutaService } from './ruta.service';

import { PlacaGridComponent } from './placa-grid.component';
import { PlacaDetailComponent } from './placa-detail.component';
import { PlacaEditComponent } from './placa-edit.component';
import { RutaEditComponent } from './ruta-edit.component';
import { RutaDetailComponent } from './ruta-detail.component';
import { RutaGridComponent } from './ruta-grid.component';

@NgModule( {
    imports: [
        CommonModule,
        SharedModule,
        MaestroRoutingModule
    ],
    declarations: [PlacaGridComponent, PlacaDetailComponent, PlacaEditComponent, RutaEditComponent, RutaDetailComponent, RutaGridComponent],
    providers: [
        PlacaService, RutaService
    ]
} )
export class MaestroModule { }
