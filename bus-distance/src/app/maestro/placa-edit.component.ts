import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';

import { PlacaService } from './placa.service';

@Component( {
    selector: 'app-placa-edit',
    templateUrl: './placa-edit.component.html'
} )
export class PlacaEditComponent implements OnInit {
    model: any;
    vhclList: any[];

    action: string;

    constructor(
        private location: Location
        , private route: ActivatedRoute
        , private router: Router
        , private modelService: PlacaService
    ) {
    }

    ngOnInit() {
        this.route.params.subscribe( params => {
            this.action = params['action'];

            this.modelService.edit( this.action, { id: +params['id'] } )
                .subscribe( resp => {
                    this.model = resp.model;

                    this.vhclList = resp.vhclList;
                } );
        } );
    }

    save() {
        this.modelService.save( this.action, this.model )
            .subscribe( resp => {
                this.action == 'edit' ? this.location.back()
                    : this.router.navigate( ['/maestro/placa/detail', resp.model.id], { replaceUrl: true } );
            } );
    }

    cancel() {
        this.location.back();
    }
}

